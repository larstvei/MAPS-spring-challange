;; This file contains a list of words. How many unique words are there in
;; the list?
;;
;; For example; if the list consisted only of the words "wow", "such",
;; "list" and "wow". Then the answer would be 3.

(ns maps-spring-challenge.wordlist.clj
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(->> (io/file (io/resource "wordlist.dat"))
     (slurp)
     (str/split-lines)
     (into #{})
     (count))

;; => 109582
