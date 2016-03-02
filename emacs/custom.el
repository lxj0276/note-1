;; root编辑
(defun sudo-find-file (file-name)
  "Like find file, but opens the file as root."
  (interactive "Sudo Find File: ")
  (let ((tramp-file-name (concat "/sudo::" (expand-file-name file-name))))
    (find-file tramp-file-name)))

;;;input * before close chinese input-method
(defun org-mode-my-init ()
  (define-key org-mode-map (kbd "×") (kbd "*"))
  (define-key org-mode-map (kbd "－") (kbd "-"))
  (set-face-attribute 'org-level-1 nil :height 1.2 :bold t)
  (set-face-attribute 'org-level-2 nil :height 1.1 :bold t)
  (set-face-attribute 'org-level-3 nil :height 1.1 :bold t)
  )

(add-hook 'org-mode-hook 'org-mode-my-init)

;;显示日期
(setq display-time-day-and-date t)
;;显示时间
(display-time)
;;时间为24小时制
(setq display-time-24hr-format t)
;;时间显示包括日期和具体时间
(setq display-time-day-and-date t)

;;;添加全局TODO目录（note）
;(setq org-agenda-files (list "/home/kay/note/"
;                           ;  "/home/kay/note/TODO.org"
;                             ))
;(setq org-agenda-files (list "/home/kay/Work/"
;                                        ;  "/home/kay/note/TODO.org"
;                             ))


;;标题格式, "文件名  @  全路径文件名"
(setq frame-title-format '("%b   @   " buffer-file-name))

;; 显示行号
(require 'linum)
(global-linum-mode t)

;;orgmode 中插入代码
;;按 M-x org-insert-src-block ，然后输入代码类型（如 emacs-lisp，按 TAB 可自动补全）即可。
(defun org-insert-src-block (src-code-type)
  "Insert a `SRC-CODE-TYPE' type source code block in org-mode."
  (interactive
   (let ((src-code-types
          '("scala"  "java" "sh" "python" "C" "js" "emacs-lisp" "clojure" "C++" "css"
            "calc" "asymptote" "dot" "gnuplot" "ledger" "lilypond" "mscgen"
            "octave" "oz" "plantuml" "R" "sass" "screen" "sql" "awk" "ditaa"
            "haskell" "latex" "lisp" "matlab" "ocaml" "org" "perl" "ruby"
            "scheme" "sqlite" )))
     (list (ido-completing-read "Source code type: " src-code-types))))
  (progn
    (newline-and-indent)
    (insert (format "#+BEGIN_SRC %s\n" src-code-type))
    (newline-and-indent)
    (insert "#+END_SRC\n")
    (previous-line 2)
    (org-edit-src-code)))

(global-set-key (kbd "C-i") 'anaconda-mode-find-definitions)
(global-set-key (kbd "<C-tab>") 'anaconda-mode-complete)

(global-set-key (kbd "M-j") 'spacemacs/evil-goto-next-line-and-indent)

;(global-set-key (kbd "M-?") 'jedi:show-doc)
;(global-set-key (kbd "M-.") 'jedi:goto-definition)
;(global-set-key (kbd "M-,") 'jedi:goto-definition-pop-marker)
;;插入代码高亮
;(setq org-src-fontify-natively t)


(provide 'custom)
