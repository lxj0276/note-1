;; root编辑
(defun sudo-find-file (file-name)
  "Like find file, but opens the file as root."
  (interactive "FSudo Find File: ")
  (let ((tramp-file-name (concat "/sudo::" (expand-file-name file-name))))
    (find-file tramp-file-name)))

;;indent
(defun my-indent-region (N)
  (interactive "p")
  (if (use-region-p)
      (progn (indent-rigidly (region-beginning) (region-end) (* N 4))
             (setq deactivate-mark nil))
    (self-insert-command N)))

(defun my-unindent-region (N)
  (interactive "p")
  (if (use-region-p)
      (progn (indent-rigidly (region-beginning) (region-end) (* N -4))
             (setq deactivate-mark nil))
    (self-insert-command N)))

(global-set-key ">" 'my-indent-region)
(global-set-key "<" 'my-unindent-region)
;; 显示行号
(require 'linum)
(global-linum-mode t)

;;;input * before close chinese input-method
(defun org-mode-my-init ()
  (define-key org-mode-map (kbd "×") (kbd "*"))
  (define-key org-mode-map (kbd "－") (kbd "-"))
  )

;(setq-default dotspacemacs-configuration-layers '(auto-completion))

(add-hook 'org-mode-hook 'org-mode-my-init)

;; org 自动换行
(add-hook 'org-mode-hook
          (lambda () (setq truncate-lines nil)))

;;显示日期
(setq display-time-day-and-date t)
;;显示时间
(display-time)
;;时间为24小时制
(setq display-time-24hr-format t)
;;时间显示包括日期和具体时间
(setq display-time-day-and-date t)

;;标题格式, "文件名  @  全路径文件名"
(setq frame-title-format '("%b   @   " buffer-file-name))

;;orgmode中插入代码
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

;;插入代码高亮
(setq org-src-fontify-natively t)

(global-set-key (kbd "C-i") (kbd "SPC m g g"))
(global-set-key (kbd "<C-tab>") 'anaconda-mode-complete)
(global-set-key (kbd "M-j") 'spacemacs/evil-goto-next-line-and-indent)
;;; Standard Jedi.el setting
;(setq jedi:setup-keys t)
;(add-hook 'python-mode-hook 'jedi:setup)
;(add-hook 'python-mode-hook 'jedi:ac-setup)
;(setq jedi:complete-on-dot t)
;(define-key python-mode-map (kbd "M-.") 'jedi:goto-definition)
;(define-key python-mode-map (kbd "M-,") 'jedi:goto-definition-pop-marker)
;(define-key python-mode-map (kbd "M-?") 'jedi:show-doc)
;(define-key python-mode-map (kbd "<C-tab>") 'jedi:complete)
;
;; Type:
;;     M-x package-install RET jedi RET
;;     M-x jedi:install-server RET
;; Then open Python file.
(setq which-key-allow-evil-operators t)
(setq initial-frame-alist (quote ((fullscreen . maximized))))

;; 有道快捷键绑定
(define-key global-map (kbd "C-c k") 'youdao-dictionary-search-at-point+)
(define-key global-map (kbd "C-c s") 'youdao-dictionary-search-at-point)
(define-key global-map (kbd "C-c i") 'youdao-dictionary-search-from-input)

;; 原生快捷键
(define-key evil-normal-state-map "\C-y" 'yank)
(define-key evil-insert-state-map "\C-y" 'yank)
(define-key evil-visual-state-map "\C-y" 'yank)
(define-key evil-insert-state-map "\C-e" 'end-of-line)
(define-key evil-insert-state-map "\C-a" 'move-beginning-of-line)
;; (define-key evil-insert-state-map (kbd "C-d b") 'backward-delete-char)
;; (define-key evil-insert-state-map (kbd "C-d f") 'backward-delete-char)
(define-key evil-insert-state-map "\C-n" 'next-line)
(define-key evil-insert-state-map "\C-p" 'previous-line)
(define-key evil-insert-state-map "\C-k" 'kill-line)
(define-key evil-insert-state-map "\C-r" 'search-backward)

(define-key global-map (kbd "C-+") 'text-scale-increase)
(define-key global-map (kbd "C--") 'text-scale-decrease)

(custom-set-variables
 '(py-force-py-shell-name-p t)
 '(py-shell-name "python2.7"))

;; emacs中的中文输入法
(add-to-list 'load-path "~/.emacs.d/site-lisp/eim")
(autoload 'eim-use-package "eim" "Another emacs input method")
;; Tooltip 暂时还不好用
(setq eim-use-tooltip nil)

(register-input-method
 "eim-py" "euc-cn" 'eim-use-package
 "拼音" "汉字拼音输入法" "py.txt")

;; 用 ; 暂时输入英文
(require 'eim-extra)
(global-set-key ";" 'eim-insert-ascii)

;; For GTD
(setq org-todo-keywords
      '((sequence "TODO(t!)" "NEXT(n)" "WAITTING(w)" "SOMEDAY(s)" "|;" "DONE(d@/!)" "ABORT(a@/!)")
       ))  
;;(setq ort/capture-todo
      ;;'(("i" tags-todo "CATEGORY=\"IDEAS\"")
        ;;("r" tags-todo "CATEGORY=\"READING\"")
        ;;;; other custom agenda commands here
        ;;))
;; 设置默认org agent 目录
(setq org-agenda-files '("~/note"))

(provide 'custom)
