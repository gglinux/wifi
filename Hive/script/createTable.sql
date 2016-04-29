--hive wifi_record
create table wifi_record(
	device_mac string,
	record_time string,
	user_mac string,
	ap_mac string,
	data_rate string,
	rssi_signal string,
	channel_id string,
	app_type string,
	app_info string
) 
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY '\t' 
STORED AS TEXTFILE;