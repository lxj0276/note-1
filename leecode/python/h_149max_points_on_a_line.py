"""
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
"""

class Point(object):
    def __init__(self, a=0, b=0):
        self.x = a
        self.y = b


class Solution(object):
    def maxPoints(self, points):
        """
        :type points: List[Point]
        :rtype: int
        """
        result_points = set()
        # Store the gradient of every two points.
        two_points_gradients = {}
        for i in range(len(points)):
            for j in range(i+1, len(points)):
                if points[i].x - points[j].x == 0:
                    k = float('Inf')
                else:
                    k = - (points[i].y - points[j].y) / float(points[i].x - points[j].x)
                two_points_gradients[(points[i], points[j])] = k

        # max count of gradient (group by gradient)
        max_count_of_gradients = 0
        # the gradient which have the max count
        gradient_of_max_count = 0.0
        # key: gradient, value: count.
        gradient_count = {}
        print(len(two_points_gradients))
        for  k in two_points_gradients.values():
            print k
            count = gradient_count.get(k, 0)
            now_count = count + 1
            gradient_count[k] = now_count
            if now_count > max_count_of_gradients:
                max_count_of_gradients = now_count
                gradient_of_max_count = k

        for  points_tup, k in two_points_gradients.items():
            if k == gradient_of_max_count:
                result_points.add(points_tup[0])
                result_points.add(points_tup[1])
        print gradient_of_max_count
        print max_count_of_gradients
        # TODO: Is result points which get from the max count of gradient must be right?
        return len(result_points)


if __name__ == '__main__':
    p1 = Point(2, 3)
    p2 = Point(4, 6)
    p7 = Point(8, 12)
    p3 = Point(6, 9)
    p4 = Point(1, 2)
    p5 = Point(2, 4)
    p6 = Point(3, 6)
    points = []
    points.append(p1)
    points.append(p2)
    points.append(p3)
    points.append(p4)
    points.append(p5)
    points.append(p6)
    points.append(p7)
    print Solution().maxPoints(points)

