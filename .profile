
# ~/.bash_profile
#
alias pacin="sudo pacman -S"
alias yain="yaourt -S"
alias yasearch="yaourt -Ss"
alias pss="ps aux | grep"
alias cat="ccat"
alias youtube-viewer="youtube-viewer -C --video-player=mpv"
alias emacs="emacs -nw"

JAVA_HOME="/usr/local/java/jdk1.8.0_51"
CATALINA_HOME="~/software/apache-tomcat-8.0.24"
SBT_OPTS="-Xms512M -Xmx1536M -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=1024M"
export PATH=$PATH:$JAVA_HOME/bin:$JAVA_HOME/jre/bin:/usr/bin/site_perl

export CLASSPATH=.:$JAVA_HOME/lib/:$CATALINA_HOME/lib/
export GTK_IM_MODULE=fcitx
export QT_IM_MODULE=fcitx
export XMODIFIERS=@im=fcitx
export LESSOPEN="| /usr/bin/source-highlight-esc.sh %s"
export LESS=' -R -N '
source /usr/share/nvm/init-nvm.sh
