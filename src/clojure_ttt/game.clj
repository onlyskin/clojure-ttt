(ns clojure-ttt.game
  (:require [clojure-ttt.ui :refer :all])
  (:require [clojure-ttt.human-player :refer :all])
  (:require [clojure-ttt.computer-player :refer :all])
  (:require [clojure-ttt.board :refer :all]))

(defn- get-move-function [move-functions, depth]
  (as-> [depth] n
    (mod n 2)
    (get n move-functions)))

(defn- play-turn [move-functions depth board]
  (output-board board)
  (cond
    (game-over? board) (output-game-result board)
    :else (do
            (let [get-move (get move-functions (mod depth 2))]
              (->> board
                   (get-move)
                   (play-on-board board)
                   (play-turn move-functions (inc depth)))))))

(defn run-game [move-functions]
  (play-turn move-functions 0 (make-board)))

(defn- move-func-from-choice [choice]
  ({"h" get-human-move "c" get-negamax-move} choice))

(defn make-game []
  (output "Player 1 type: (h)uman or (c)omputer")
  (output "Player 2 type: (h)uman or (c)omputer")
  (run-game [(move-func-from-choice (input))
             (move-func-from-choice (input))
             ]))

