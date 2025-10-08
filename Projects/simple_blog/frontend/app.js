(function () {

  const state = {
    posts: [],
    filtered: [],
    searchQuery: '',
    sortBy: 'newest',
    initialized: false,
  };

  const els = {};
  function $(id) { return document.getElementById(id); }
  function initElements() {
    els.form = $('blogForm');
    els.postId = $('postId');
    els.title = $('title');
    els.author = $('author');
    els.content = $('content');
    els.formTitle = $('formTitle');
    els.cancelEditBtn = $('cancelEditBtn');
    els.posts = $('posts');
    els.emptyState = $('emptyState');
    els.searchInput = $('searchInput');
    els.sortSelect = $('sortSelect');
    els.postItemTemplate = document.getElementById('postItemTemplate');
  }

  const API_BASE = '';
  async function apiList() {
    const res = await fetch(`${API_BASE}/api/posts`);
    if (!res.ok) throw new Error('Failed to fetch posts');
    return res.json();
  }
  async function apiCreate(data) {
    const res = await fetch(`${API_BASE}/api/posts`, { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(data) });
    if (!res.ok) throw new Error('Failed to create post');
    return res.json();
  }
  async function apiUpdate(id, data) {
    const res = await fetch(`${API_BASE}/api/posts/${encodeURIComponent(id)}`, { method: 'PUT', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(data) });
    if (!res.ok) throw new Error('Failed to update post');
    return res.json();
  }
  async function apiDelete(id) {
    const res = await fetch(`${API_BASE}/api/posts/${encodeURIComponent(id)}`, { method: 'DELETE' });
    if (!res.ok) throw new Error('Failed to delete post');
  }

  function render() {
    const { filtered } = state;
    els.posts.innerHTML = '';
    if (!filtered.length) {
      els.emptyState.hidden = false;
      return;
    }
    els.emptyState.hidden = true;
    const frag = document.createDocumentFragment();
    for (const post of filtered) {
      const li = els.postItemTemplate.content.firstElementChild.cloneNode(true);
      li.dataset.id = post.id;
      li.querySelector('.post-title').textContent = post.title;
      li.querySelector('.post-meta').textContent = `${post.author} • ${new Date(post.updatedAt).toLocaleString()}`;
      li.querySelector('.post-content').textContent = post.content;
      li.querySelector('.edit').addEventListener('click', () => startEdit(post.id));
      li.querySelector('.delete').addEventListener('click', () => deletePost(post.id));
      frag.appendChild(li);
    }
    els.posts.appendChild(frag);
  }

  function applyFiltersAndSort() {
    const q = state.searchQuery.trim().toLowerCase();
    let list = [...state.posts];
    if (q) {
      list = list.filter(p =>
        p.title.toLowerCase().includes(q) ||
        p.author.toLowerCase().includes(q) ||
        p.content.toLowerCase().includes(q)
      );
    }
    switch (state.sortBy) {
      case 'oldest': list.sort((a,b)=>a.createdAt-b.createdAt); break;
      case 'title': list.sort((a,b)=>a.title.localeCompare(b.title)); break;
      case 'author': list.sort((a,b)=>a.author.localeCompare(b.author)); break;
      default: list.sort((a,b)=>b.createdAt-a.createdAt);
    }
    state.filtered = list;
    render();
  }

  function resetForm() {
    els.form.reset();
    els.postId.value = '';
    els.formTitle.textContent = 'Create New Post';
    els.cancelEditBtn.hidden = true;
  }

  function startEdit(id) {
    const post = state.posts.find(p => p.id === id);
    if (!post) return;
    els.postId.value = post.id;
    els.title.value = post.title;
    els.author.value = post.author;
    els.content.value = post.content;
    els.formTitle.textContent = 'Edit Post';
    els.cancelEditBtn.hidden = false;
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  async function upsertPost(data) {
    if (data.id) {
      const updated = await apiUpdate(data.id, { title: data.title, author: data.author, content: data.content });
      const idx = state.posts.findIndex(p => p.id === data.id);
      if (idx !== -1) state.posts[idx] = updated;
    } else {
      const created = await apiCreate({ title: data.title, author: data.author, content: data.content });
      state.posts.unshift(created);
    }
    applyFiltersAndSort();
    resetForm();
  }

  async function deletePost(id) {
    if (!confirm('Delete this post?')) return;
    await apiDelete(id);
    state.posts = state.posts.filter(p => p.id !== id);
    applyFiltersAndSort();
  }

  async function handleSubmit(e) {
    e.preventDefault();
    const data = {
      id: els.postId.value || undefined,
      title: els.title.value.trim(),
      author: els.author.value.trim(),
      content: els.content.value.trim(),
    };
    if (!data.title || !data.author || !data.content) {
      alert('Please fill in title, author, and content.');
      return;
    }
    try {
      await upsertPost(data);
    } catch (err) {
      alert('Operation failed. Please try again.');
      console.error(err);
    }
  }

  function wireEvents() {
    els.form.addEventListener('submit', handleSubmit);
    els.cancelEditBtn.addEventListener('click', resetForm);
    els.searchInput.addEventListener('input', () => { state.searchQuery = els.searchInput.value; applyFiltersAndSort(); });
    els.sortSelect.addEventListener('change', () => { state.sortBy = els.sortSelect.value; applyFiltersAndSort(); });
  }

  function bootstrap(posts) {
    state.posts = posts;
    state.initialized = true;
    applyFiltersAndSort();
  }

  async function init() {
    initElements();
    wireEvents();
    try {
      const posts = await apiList();
      bootstrap(posts);
    } catch (err) {
      console.error(err);
      bootstrap([]);
    }
  }

  document.addEventListener('DOMContentLoaded', init);
})();