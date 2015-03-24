;; This file contains a list of name and interest-pairs, indicating that a
;; person likes a certain thing. For example, the first line says that Aiden
;; likes DTrace. Naturally, a person can have multiple interests and this is
;; reflected in the dataset.
;; The dataset is not sorted.
;;
;; Dungeons & Dragons, Magic: The Gathering, Star Wars, Pokémon and Comics
;; are considered geeky interests. Any person who likes any of these things
;; is a geek. Similarly, Category Theory, DTrace, Chess, Linear
;; Algebra,Quantum Mechanics and Neuroscience are considered nerdy
;; interests. Any person who like any of those things is a nerd.
;;
;; How many individuals in the dataset are both a geek and a nerd?

(ns maps-spring-challenge.geeks-and-nerds
  (:require [clojure.string :as str]
            [clojure.set :as set]
            [clojure.java.io :as io]))

(defn geeky? [x]
  (some #(= x %) ["Dungeons & Dragons" "Magic: The Gathering"
                  "Star Wars" "Pokémon" "Comics"]))

(defn geek-or-nerd [geek-nerd-map [individual interest]]
  (let [key (if (geeky? interest) :geeks :nerds)]
    (update-in geek-nerd-map [key] conj individual)))

(let [sets (->> (io/file (io/resource "geeks_and_nerds.dat"))
                (slurp)
                (str/split-lines)
                (map #(str/split % #" " 2))
                (reduce geek-or-nerd {:geeks #{} :nerds #{}}))]
  (count (set/intersection (:geeks sets) (:nerds sets))))

;; => 69
