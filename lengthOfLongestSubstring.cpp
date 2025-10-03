class Solution {
    //  Sliding Window + HashSet
public:
    int lengthOfLongestSubstring(string s) {
        unordered_set<char> freq;
        int i = 0;
        int maxLength = 0;

        for(int j=0; j<s.size(); j++){
            // doing this while mistake
            while(freq.find(s[j]) != freq.end()){   // tb tkk remove kro jb tk woh element bahr na nikl jaye
                freq.erase(s[i]);
                i++;
            }
            freq.insert(s[j]);
            maxLength = max(maxLength,j-i+1);
        }
        return maxLength;
    }
};
