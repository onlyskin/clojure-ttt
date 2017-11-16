(ns clojure-ttt.board)

(declare current-marker)
(declare empty-cell?)
(declare win-paths)
(declare win-in-path?)
(declare full?)

(defn make-board [] (vec (repeat 9 " ")))

(defn play-on-board
  [board, position]
  (assoc board (- position 1) (current-marker board)))

(defn available-moves [board]
  (->> board
       (map-indexed #(vec [%1, %2]))
       (filter #(empty-cell? %))
       (map #(+ 1 (get % 0)))))

(defn winner? [board marker]
   (boolean
     (some #(win-in-path? board marker %) (win-paths))))

(defn winner [board]
  (cond
    (= true (winner? board "X")) "X"
    (= true (winner? board "O")) "O"))

(defn tie? [board]
  (and
    (full? board)
    (not (winner? board "X"))
    (not (winner? board "O"))))

(defn game-over? [board]
  (or
    (winner? board "X")
    (winner? board "O")
    (tie? board)))

(defn- empty-cell? [cell]
  (= " " (get cell 1)))

(defn- rows []
  (let [n 3]
    (->> (range n)
         (map #(vec [(+ 0 (* n %)) (+ 1 (* n %)) (+ 2 (* n %))]))
         (vec))))

(defn- columns [] (apply mapv vector (rows)))

(defn- diagonals [] [[0 4 8] [6 4 2]])

(defn- win-paths [] (concat (rows) (columns) (diagonals)))

(defn- win-in-path? [board marker path]
  (every? #(= % marker) (map #(get board %) path)))

(defn- full? [board]
  (not-any? #(= " " %) board))

(defn- current-marker [board]
  (as-> board v
    (available-moves v)
    (count v)
    (mod v 2)
    (get ["O" "X"] v)))
