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
                "1|2|3\n-----\n4|5|6\n-----\n7|8|9\n\n"
                (with-out-str (output-board (make-board)))))

          (it "prints board with markers"
              (should=
                "1|X|O\n-----\n4|5|6\n-----\n7|8|9\n\n"
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

(describe "get-choice"
          (it "outputs first prompt"
              (should=
                "prompt\n"
                (with-out-str
                  (with-in-str "b\n"
                    (get-choice "prompt" "erro" ["a" "b"])))))

          (it "gets choice from input"
              (should=
                "b"
                (with-in-str "b\n"
                  (get-choice "prompt" "error" ["a" "b"]))))
          
          (it "only accepts input which is in choices"
              (should=
                "a"
                (with-in-str "x\na\n"
                  (get-choice "prompt" "error" ["a" "b"]))))
          
          (it "prints error-prompt when first choice is invalid"
              (should=
                "prompt\nerror\n"
                (with-out-str
                  (with-in-str "x\na\n"
                    (get-choice "prompt" "error" ["a" "b"]))))))

(describe "get-choice-from-map"
          (with-stubs)

          (it "calls get-choice with the prompts and keys"
              (with-redefs
                [get-choice (stub :get-choice {:return "2"})]

                (get-choice-from-map :prompt :error {"1" "x" "2" "y"})

                (should-have-invoked
                  :get-choice
                  {:with [:prompt :error ["1" "2"]]})))

          (it "returns value corresponding to input key"
              (with-redefs
                [get-choice (stub :get-choice {:return "2"})]

                (get-choice-from-map :prompt :error {"1" "x" "2" "y"})

                (should=
                  "y"
                  (get-choice-from-map :prompt :error {"1" "x" "2" "y"})))))

(describe "get-move"
          (with-stubs)

          (it "calls get-choice with string list of available moves"
              (with-redefs
                [get-choice (stub :get-choice {:return "8"})]

                (get-move (vec-for-string "X  OOXX  "))

                (should-have-invoked
                  :get-choice
                  {:with
                   ["Choose a move:"
                    "Please choose a valid move:"
                    ["2" "3" "8" "9"]]})))

          (it "returns the result of get-choice as an int"
              (with-redefs
                [get-choice (stub :get-choice {:return "8"})]

                (should=
                  8
                  (get-move (vec-for-string "X  OOXX  "))))))
