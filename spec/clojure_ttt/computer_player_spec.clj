(ns clojure-ttt.computer-player-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.computer-player :refer :all]))

(defn- vec-for-string [board-string]
  (clojure.string/split board-string #""))

(describe "get-negamax-move"
          (it "X takes winning move"
              (should=
                6
                (get-negamax-move
                  (vec-for-string "XOX O OXX"))))
          
          (it "X takes winning move"
              (should=
                7
                (get-negamax-move
                  (vec-for-string "XO XO    "))))
          
          (it "O takes winning move"
              (should=
                6
                (get-negamax-move
                  (vec-for-string "XX OO X  "))))
          
          (it "blocks opponent winning move"
              (should=
                3
                (get-negamax-move
                  (vec-for-string "X  XO O  "))))
          
          (it "O plays in centre"
              (should=
                5
                (get-negamax-move
                  (vec-for-string "X        "))))) 
