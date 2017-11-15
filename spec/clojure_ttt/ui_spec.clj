(ns clojure-ttt.ui-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.board :refer :all]
            [clojure-ttt.ui :refer :all]))

(describe "output"
          (it "prints message out"
              (should= "test message\n"
                       (with-out-str
                         (output "test message")))))

(describe "output-board"
          (it "prints board out"
              (should=
                "1|2|3\n-----\n4|5|6\n-----\n7|8|9\n"
                (with-out-str (output-board (make-board)))))
          (it "prints board with markers on"
              (should=
                "1|X|O\n-----\n4|5|6\n-----\n7|8|9\n"
                (with-out-str
                  (output-board
                    [" " "X" "O" " " " " " " " " " " " "])))))

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
