(ns clojure-ttt.ui-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.board :refer :all]
            [clojure-ttt.ui :refer :all]))

(defn- vec-for-string [board-string]
  (clojure.string/split board-string #""))

(describe "output"
          (it "prints message out"
              (should= "test message\n"
                       (with-out-str
                         (output "test message")))))

(describe "output-board"
          (it "prints empty board"
              (should=
                "1|2|3\n-----\n4|5|6\n-----\n7|8|9\n"
                (with-out-str (output-board (make-board)))))

          (it "prints board with markers"
              (should=
                "1|X|O\n-----\n4|5|6\n-----\n7|8|9\n"
                (with-out-str
                  (output-board (vec-for-string " XO      "))))))

(describe "output-game-result"
          (it "prints X won"
              (should-contain "X won" (with-out-str
                  (output-game-result
                    (vec-for-string "XXXOO    ")))))

          (it "prints O won"
              (should-contain "O won" (with-out-str
                  (output-game-result
                    (vec-for-string "XX OOOX  ")))))

          (it "prints tie"
              (should-contain "tie" (with-out-str
                  (output-game-result
                    (vec-for-string "XOXOOXXXO"))))))

(describe "input"
          (it "gets input"
              (should=
                "test input"
                (with-in-str "test input\n" (input ""))))

          (it "outputs prompt"
              (should=
                "prompt-string\n"
                (with-out-str (with-in-str "test input\n"
                                (input "prompt-string"))))))

(describe "input-integer"
          (it "gets integer"
              (should= 3 (with-in-str "3\n" (input-integer))))

          (it "rejects string"
              (should= 3 (with-in-str "st\n3\n" (input-integer)))))

(describe "input-move"
          (it "gets integer"
              (should= 3 (with-in-str
                           "3\n"
                           (input-move
                             (vec-for-string "         ")))) )

          (it "rejects string"
              (should= 3 (with-in-str
                           "st\n3\n"
                           (input-move
                             (vec-for-string "         ")))))

          (it "rejects unavailable moves"
              (should= 8 (with-in-str
                           "3\n20\n8\n"
                           (input-move
                             (vec-for-string "XXOOOX   "))))))

