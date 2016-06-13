import MySQLdb

class MysqlUtils:
    """mysql utils, for create connect, close et at"""

    def __init__(self, host='localhost', port=3306, username,
                 passwd, charset="utf8"):
        """初始化"""
        self.host = host
        self.port = port
        self.username = username
        self.passwd = passwd
        self.charset=charset

        try:
            self.conn=MySQLdb.connect(host=self.host,port=self.port,
                                      user=self.user,passwd=self.password)
            self.conn.autocommit(False)
            self.conn.set_character_set(self.charset)
            self.cur=self.conn.cursor()
        except MySQLdb.Error as e:
            print("Mysql Error %d: %s" % (e.args[0], e.args[1]))

