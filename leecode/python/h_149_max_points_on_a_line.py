
"""
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
"""
# A line is determined by two factors,say y=ax+b
# 
# If two points(x1,y1) (x2,y2) are on the same line(Of course). 

# Consider the gap between two points.

# We have (y2-y1)=a(x2-x1),a=(y2-y1)/(x2-x1) a is a rational, b is canceled since b is a constant

# If a third point (x3,y3) are on the same line. So we must have y3=ax3+b

# Thus,(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a

# Since a is a rational, there exists y0 and x0, y0/x0=(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a

# So we can use y0&x0 to track a line;

"""
Use a map<Double, Set<Point>> to track points which have same gradient, and then output the max size of set
"""
