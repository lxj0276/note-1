""" E
Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
"""

# class Solution {
# public: 
#     int strStr(string haystack, string needle) {
#         int m = haystack.length(), n = needle.length();
#         if (!n) return 0;
#         for (int i = 0; i < m - n + 1; i++) {
#             int j = 0;
#             for (; j < n; j++)
#                 if (haystack[i + j] != needle[j])
#                     break;
#             if (j == n) return i;
#         }
#         return -1;
#     }
# }

# public class Solution {
#     public int strStr(String haystack, String needle) {
#         int l1 = haystack.length(), l2 = needle.length();
#         if (l1 < l2) {
#             return -1;
#         }
#         if (l2 == 0) {
#             return 0;
#         }
#         int threshold = l1 - l2;
#         for (int i = 0; i <= threshold; ++i) {
#             if (haystack.substring(i,i+l2).equals(needle)) {
#                 return i;
#             }
#         }
#         return -1;
#     }
# }
