(ns clojure-ttt.game-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.board :refer :all]
            [clojure-ttt.game :refer :all]
            [clojure-ttt.computer-player :refer :all]
            [clojure-ttt.human-player :refer :all])) 

(defn- get-stub-move [board]
  (cond
    (boolean (some #(= % 4) (available-moves board))) 4
    :else 5))

(defn out-str-from-h-h-game [moves]
  (with-out-str
    (with-in-str
      (clojure.string/join "\n" moves)
      (run-game [get-human-move get-human-move]))))

(defn out-str-from-h-other-game [moves]
  (with-out-str
    (with-in-str
      (clojure.string/join "\n" moves)
      (run-game [get-human-move get-stub-move]))))

(describe "run human human game"
          (it "ends" (should true))

          (it "output prints empty board and markers"
              (should-contain
                #"(?s)1.*2.*3.*4.*5.*6.*7.*8.*9"
                (out-str-from-h-h-game ["1" "4" "2" "5" "3"])) 
              
              (should-contain
                "O"
                (out-str-from-h-h-game ["1" "4" "2" "5" "3"]))

              (should-contain
                "X"
                (out-str-from-h-h-game ["1" "4" "2" "5" "3"]))
              )

          (it "output prints X won"
              (should-contain
                "X won"
                (out-str-from-h-h-game ["1" "4" "2" "5" "3"])))

          (it "rejects bad moves"
              (should-contain
                "X won"
                (out-str-from-h-h-game
                  ["1" "4" "st" "2" "5" "20" "3"]))))

(describe "run human other game"
          (it "output prints X won"
              (should-contain
                "X won"
                (out-str-from-h-other-game ["1" "2" "3"]))))

(describe "make game"
          (with-stubs)

          (it "prints options"
              (with-redefs
                [run-game (stub :run-game)]
               (should-contain
                 #"(?s)Player 1 type: \(h\)uman or \(c\)omputer"
                 (with-out-str
                   (with-in-str "h\nh\n" (make-game))))
               (should-contain
                 #"(?s)Player 2 type: \(h\)uman or \(c\)omputer"
                 (with-out-str
                   (with-in-str "h\nh\n" (make-game))))))

          (it "calls run-game with human move funcs when input is human human"
              (with-redefs
                [run-game (stub :run-game)]
                (with-in-str "h\nh\n" (make-game))

                (should-have-invoked
                  :run-game
                  {:with [[get-human-move get-human-move]]})))

          (it "calls run-game with human move and negamax move when input is human computer"
              (with-redefs
                [run-game (stub :run-game)]
                (with-in-str "h\nc\n" (make-game))

                (should-have-invoked
                  :run-game
                  {:with [[get-human-move get-negamax-move]]})))

          (it "calls run-game with negamax move and human move when input is computer human"
              (with-redefs
                [run-game (stub :run-game)]
                (with-in-str "c\nh\n" (make-game))

                (should-have-invoked
                  :run-game
                  {:with [[get-negamax-move get-human-move]]})))

          )
