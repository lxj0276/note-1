"""
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
"""
class Solution:
    # @return a boolean
    def isValid(self, s):
        stack = []
        dict = {"]":"[", "}":"{", ")":"("}
        for char in s:
            if char in dict.values():
                stack.append(char)
            elif char in dict.keys():
                if stack == [] or dict[char] != stack.pop():
                    return False
            else:
                return False
        return stack == []


#include <stack>

# class Solution {
# public:
#     bool isValid(string s) {
#         stack<char> paren;
#         for (char& c : s) {
#             switch (c) {
#                 case '(': 
#                 case '{': 
#                 case '[': paren.push(c); break;
#                 case ')': if (paren.empty() || paren.top()!='(') return false; else paren.pop(); break;
#                 case '}': if (paren.empty() || paren.top()!='{') return false; else paren.pop(); break;
#                 case ']': if (paren.empty() || paren.top()!='[') return false; else paren.pop(); break;
#                 default: ; // pass
#             }
#         }
#         return paren.empty() ;
#     }
# };
