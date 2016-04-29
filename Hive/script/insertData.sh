#!bin/bash

#create table
if[$HIVE_PATH==""]
then 
	echo "HIVE_PATH is undefined"
	exit
hive -f ./createTable.sql

#insert data
for file_n in `ls /hive_dat/getui/*log`
do
hive -S -e"load data local inpath '$file_n' into  table report_im_push_msg_log;"
done