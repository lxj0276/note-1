"""
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
"""

"""
p is the parenthesis-string built so far, left and right tell the number of left and right parentheses still to add, and parens collects the parentheses.

Solution 1

I used a few "tricks"... how many can you find? :-)

def generateParenthesis(self, n):
    def generate(p, left, right, parens=[]):
        if left:         generate(p + '(', left-1, right)
        if right > left: generate(p + ')', left, right-1)
        if not right:    parens += p,
        return parens
    return generate('', n, n)
Solution 2

Here I wrote an actual Python generator. I allow myself to put the yield q at the end of the line because it's not that bad and because in "real life" I use Python 3 where I just say yield from generate(...).

def generateParenthesis(self, n):
    def generate(p, left, right):
        if right >= left >= 0:
            if not right:
                yield p
            for q in generate(p + '(', left-1, right): yield q
            for q in generate(p + ')', left, right-1): yield q
    return list(generate('', n, n))
Solution 3

Improved version of this. Parameter open tells the number of "already opened" parentheses, and I continue the recursion as long as I still have to open parentheses (n > 0) and I haven't made a mistake yet (open >= 0).

def generateParenthesis(self, n, open=0):
    if n > 0 <= open:
        return ['(' + p for p in self.generateParenthesis(n-1, open+1)] + \
               [')' + p for p in self.generateParenthesis(n, open-1)]
    return [')' * open] * (not n)
"""

 # public List<String> generateParenthesis(int n) {
 #        List<String> list = new ArrayList<String>();
 #        backtrack(list, "", 0, 0, n);
 #        return list;
 #    }
    
 #    public void backtrack(List<String> list, String str, int open, int close, int max){
        
 #        if(str.length() == max*2){
 #            list.add(str);
 #            return;
 #        }
        
 #        if(open < max)
 #            backtrack(list, str+"(", open+1, close, max);
 #        if(close < open)
 #            backtrack(list, str+")", open, close+1, max);
 #    }
