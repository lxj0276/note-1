"""
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.
"""
import re

class Solution(object):
    def isNumber(self, s):
        """
        :type s: str
        :rtype: bool
        """
        if re.search(r'^[+-]?\d+([e\.]?\d+)?$', s):
            return True
        else:
            return False


if __name__ == '__main__':
    print Solution().isNumber("3")
    print Solution().isNumber("3e2")
    print Solution().isNumber("3.2") 
    print Solution().isNumber("-3.2")
    print Solution().isNumber("-3.2e")
    print Solution().isNumber("-3.a2e")

