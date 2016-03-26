#!/bin/bash
num=$(awk 'BEGIN{for(i=0.1;i<=5;i+=0.1)print i}')
for lambda in $num
do
 for (( n=1; n <= 4; n++ ))
 do
  echo "n is ${n}"
  echo "lambda is ${lambda}"
  scala -cp lin567_p1.jar lin567_p1.Run ../../../train.labeled ../../../dev.labeled $n $lambda |../../.././evaluate.pl -gold ../../../dev.labeled
  echo "stats is "
 done
done
