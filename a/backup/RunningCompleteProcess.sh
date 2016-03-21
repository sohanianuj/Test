#!/bin/bash

export HADOOP_HOME=/usr/local/hadoop
export PATH=$PATH:$HADOOP_HOME/bin:$JAVA_PATH/bin:$HADOOP_HOME/sbin
export HIVE_HOME=/usr/local/hive
export PATH=$PATH:$HIVE_HOME/bin

#Generate Input Data with Data generator.jar

if
hadoop jar /home/IMPETUS/anuj.sohani/DataGenerator/Datagenerator.jar 1 100000 /user/hadoop/casestudy/datafile1
then
echo Successful..
else
exit 1
fi
hadoop fs -rm /user/hadoop/casestudy/datafile1/_SUCCESS
if
hadoop jar /home/IMPETUS/anuj.sohani/Desktop/casestudy.jar mapReduce.CaseStudy /user/hadoop/casestudy/datafile1 output
then
echo Successful....
else
exit 1
fi
hadoop fs -rm /user/hduser/output/_SUCCESS
if
hive -f /home/hduser/hive_query.hql
then
echo Successful...
else
exit 1
fi
if
hadoop jar /home/IMPETUS/anuj.sohani/Desktop/casestudy.jar mapReduce.CaseStudy /user/hadoop/casestudy/validationfile output1
then
echo Successful..
else
exit 1
fi

