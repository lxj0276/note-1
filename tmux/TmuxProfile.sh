#!/bin/bash

tmux has-session -t base
if [ $? != 0 ]
then
    tmux new-session -s base -d
    tmux split-window -h -t base
    # tmux select-layout -t base main-horizontal
    tmux new-window -t base
    tmux split-window -h -t base
    tmux split-window -v -t base:1.0
    tmux split-window -v -t base:1.1
    # tmux send-keys -t base:0.0 '' C-m
    # tmux send-keys -t base:0.1 '' C-m
    tmux new-window -t base
    tmux send-keys -t base:2 'emacs -nw' C-m
    tmux select-window -t base:0.0
fi
tmux attach -t base
tmux rename-window -t 0 two
tmux rename-window -t 1 four
