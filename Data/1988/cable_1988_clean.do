// cable_1988_clean
set more off
clear all


// directory
local dir "/Volumes/SanDisk/Cable/Output/1988/" // directory for excel files
local output_dir "/Volumes/SanDisk/Cable/Data/1988/" // direcitory for output

cd `output_dir'


//set data name
local dta_name "cable_1988.dta"            /*set data name*/


// state list
#delimit;

local statenames "Alaska Arizona Arkansas California Colorado Connecticut Delaware 
DC Florida 
Georgia Hawaii Idaho Illinois Indiana Iowa Kansas Kentucky Louisiana Maine Maryland 
Massachusetts Michigan Minnesota Mississippi Missouri Montana Nebraska Nevada 
NewHampshire NewJersey NewMexico NewYork NorthCarolina NorthDakota Ohio Oklahoma 
Oregon Pennsylvania RhodeIsland SouthCarolina SouthDakota Tennessee Texas Utah 
Vermont Virginia Washington WestVirginia Wisconsin Wyoming";

#delimit cr;


// initial dta file
import excel "`dir'Alabama/Alabama.xlsx", sheet("Sheet1") firstrow allstring
save `dta_name', replace
clear


// for loop: append xlsx files
foreach var of local statenames {
	import excel "`dir'`var'/`var'.xlsx", sheet("Sheet1") firstrow allstring
	append using `dta_name'
	save `dta_name', replace
    clear    
}


// use data
use `dta_name'
sort State Copied Name


// destring variables
#delimit;

destring Copied Population BasicSubscribers ExpandedBasic1Subscribers 
ExpandedBasic2Subscribers ExpandedBasic3Subscribers Tier1Subscribers 
Tier2Subscribers Tier3Subscribers Tier4Subscribers Tier5Subscribers 
Tier6Subscribers Tier7Subscribers Tier8Subscribers HBO MTV Homespassed 
Homesinfranchisedarea Channelscapicity Channelsavailablebutnotinus, replace force;

#delimit cr;


// generate year variable
gen Year = "19" + regexs(0) if regexm(Whenservicebegan, "[0-9][0-9]$")

// Glenville, WV - service began in 2001??
replace Year = "2001" if Year == "1901"

