(ns clojure-ttt.ui
  (:require [clojure-ttt.board :refer :all]))

(defn output [message] (println message))

(defn- inner-string [index, value]
  (cond
    (not= " " value) value
    :else (str (inc index))))

(defn- cell-string [index, value] 
  (cond
    (= index 8) (inner-string index value)
    (= 2 (mod index 3)) (str (inner-string index value) "\n-----\n")
    :else (str (inner-string index value) "|")))

(defn- cell-strings [board]
  (map-indexed #(cell-string %1, %2) board))

(defn- board-str [board]
  (clojure.string/join
    (cell-strings board)))

(defn output-board [board]
  (output (board-str board))
  (output ""))

(defn output-game-result [board]
  (cond
    (winner? board "X") (output "X won.")
    (winner? board "O") (output "O won.")
    (tie? board) (output "The game was a tie.")))

(defn input
  ([prompt]
   (println prompt)
   (read-line))
  ([]
   (read-line)))

(defn- get-valid-choice [error-prompt choices]
  (let [s (input)]
    (cond
      (some #(= s %) choices) s
      :else (do
              (output error-prompt)
              (recur error-prompt choices)))))

(defn get-choice [prompt error-prompt choices]
  (output prompt)
  (get-valid-choice error-prompt choices))

(defn get-choice-from-map [prompt error-prompt choice-map]
  (->> choice-map
       (keys)
       (get-choice prompt error-prompt)
       (choice-map)))

(defn get-move [board]
  (->> board
       (available-moves)
       (map #(str %))
       (vec)
       (get-choice
         "Choose a move:"
         "Please choose a valid move:")
       (Integer/parseInt)))
