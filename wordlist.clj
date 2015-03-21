;; This file contains a list of words. How many unique words are there in
;; the list?
;;
;; For example; if the list consisted only of the words "wow", "such",
;; "list" and "wow". Then the answer would be 3.

(require '[clojure.string :as str])

(->> (slurp "wordlist.dat")
     (str/split-lines)
     (into #{})
     (count))

;; => 109582

