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

(require '[clojure.string :as str])
(require '[clojure.set :as set])

(let [geeky? (fn [x] (some #(= x %) ["Dungeons & Dragons" "Magic: The Gathering" "Star Wars"
                                     "Pokémon" "Comics"]))
      nerdy? (fn [x] (some #(= x %) ["Category Theory" "DTrace" "Chess" "Linear Algebra"
                                     "Quantum Mechanics" "Neuroscience"]))
      lines (str/split-lines (slurp "geeks_and_nerds.dat"))
      vecs (map #(str/split % #" " 2) lines)
      individuals (map first vecs)
      interests (map second vecs)]
  ;; Non-idiomatic Clojure...
  (loop [individuals individuals
         interests interests
         geeky #{}
         nerdy #{}]
    (cond (empty? individuals) (count (set/intersection geeky nerdy))
          (geeky? (first interests))
          (recur (rest individuals)
                 (rest interests)
                 (conj geeky (first individuals)) nerdy)
          (nerdy? (first interests))
          (recur (rest individuals)
                 (rest interests)
                 geeky (conj nerdy (first individuals))))))

;; => 69
