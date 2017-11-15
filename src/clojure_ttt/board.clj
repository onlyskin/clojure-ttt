(ns clojure-ttt.board)

(defn make-board [] (vec (repeat 9 " ")))

(declare current-marker)
(defn play-on-board
  [board, position]
  (assoc board (- position 1) (current-marker board)))

(declare empty-cell?)
(defn available-moves [board]
  (map
    #(+ 1 (get % 0))
    (filter
      #(empty-cell? %)
      (map-indexed #(vec [%1, %2]) board))))

(declare win-paths)
(declare win-in-path?)
(defn winner? [board marker]
   (boolean
     (some #(win-in-path? board marker %) (win-paths))))

(defn winner [board]
  (cond
    (= true (winner? board "X")) "X"
    (= true (winner? board "O")) "O"))

(declare full?)
(defn tie? [board]
  (and
    (full? board)
    (not (winner? board "X"))
    (not (winner? board "O"))))

(defn game-over? [board] (or
                           (winner? board "X")
                           (winner? board "O")
                           (tie? board)))

(defn- empty-cell? [cell]
  (= " " (get cell 1)))

(defn- rows []
  (vec
    (map
      #(vec [(+ 0 (* 3 %)) (+ 1 (* 3 %)) (+ 2 (* 3 %))])
      (range 3))))

(defn- columns [] (apply mapv vector (rows)))

(defn- diagonals [] [[0 4 8] [6 4 2]])

(defn- win-paths [] (concat (rows) (columns) (diagonals)))

(defn- win-in-path? [board marker path]
  (every? #(= % marker) (map #(get board %) path)))

(defn- full? [board] (not-any?
                       #(= " " %) board))

(defn- current-marker [board]
  (get
    ["O" "X"]
    (mod (count (available-moves board)) 2))
  )
