const path = require('path');
const fs = require('fs');
const express = require('express');

const app = express();
const PORT = process.env.PORT || 3000;
const DATA_FILE = path.join(__dirname, 'data', 'blogs.json');
const FRONTEND_DIR = path.join(__dirname, '..', 'frontend');

app.use(express.json({ limit: '1mb' }));
app.use(express.static(FRONTEND_DIR));

function ensureDataFile() {
  if (!fs.existsSync(DATA_FILE)) {
    fs.mkdirSync(path.dirname(DATA_FILE), { recursive: true });
    fs.writeFileSync(DATA_FILE, '[]\n', 'utf8');
  }
}

function readPosts() {
  ensureDataFile();
  try {
    const raw = fs.readFileSync(DATA_FILE, 'utf8');
    const data = JSON.parse(raw);
    return Array.isArray(data) ? data : [];
  } catch (e) {
    return [];
  }
}

function writePosts(posts) {
  ensureDataFile();
  fs.writeFileSync(DATA_FILE, JSON.stringify(posts, null, 2) + '\n', 'utf8');
}

function uuid() {
  return 'id-' + Math.random().toString(36).slice(2) + Date.now().toString(36);
}

app.get('/api/posts', (req, res) => {
  res.json(readPosts());
});

app.post('/api/posts', (req, res) => {
  const { title, author, content } = req.body || {};
  if (!title || !author || !content) return res.status(400).json({ error: 'Missing fields' });
  const now = Date.now();
  const post = { id: uuid(), title, author, content, createdAt: now, updatedAt: now };
  const posts = readPosts();
  posts.unshift(post);
  writePosts(posts);
  res.status(201).json(post);
});

app.put('/api/posts/:id', (req, res) => {
  const { id } = req.params;
  const { title, author, content } = req.body || {};
  const posts = readPosts();
  const idx = posts.findIndex(p => p.id === id);
  if (idx === -1) return res.status(404).json({ error: 'Not found' });
  const now = Date.now();
  posts[idx] = {
    ...posts[idx],
    title: title ?? posts[idx].title,
    author: author ?? posts[idx].author,
    content: content ?? posts[idx].content,
    updatedAt: now,
  };
  writePosts(posts);
  res.json(posts[idx]);
});

app.delete('/api/posts/:id', (req, res) => {
  const { id } = req.params;
  const posts = readPosts();
  const idx = posts.findIndex(p => p.id === id);
  if (idx === -1) return res.status(404).json({ error: 'Not found' });
  const [removed] = posts.splice(idx, 1);
  writePosts(posts);
  res.json({ ok: true, removed });
});

app.get('*', (req, res) => {
  res.sendFile(path.join(FRONTEND_DIR, 'index.html'));
});

app.listen(PORT, () => {
  console.log(`Simple Blog running at http://localhost:${PORT}`);
});