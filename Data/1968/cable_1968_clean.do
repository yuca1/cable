* cable_1968 clean up


* Destring Population
destring Population, generate(population) force


* Generate year
gen year = "19" + regexs(0) if regexm(Whenservicebegan, "[0-9][0-9]$")
destring year, replace


* Destring Subscribers
destring Subscribers, generate(subscribers) force


* Generate upper and lower bounds for Potential
gen potential_lower = regexs(1) + regexs(2) if regexm(Potential, "([0-9]*).?([0-9]*)[-][0-9]*")
gen potential_upper = regexs(1) + regexs(2) if regexm(Potential, "[0-9]*[-]([0-9]*).?([0-9]*)")

destring potential_lower, replace
destring potential_upper, replace

replace potential_lower = potential_lower*1000 if potential_lower < 100


* Generate upper and lower bounds for Homes in front of plant
gen homes_lower = regexs(1) + regexs(2) if regexm(Homeinfrontofplant, "([0-9]*).?([0-9]*)[-][0-9]*")
gen homes_upper = regexs(1) + regexs(2) if regexm(Homeinfrontofplant, "[0-9]*[-]([0-9]*).?([0-9]*)")

destring homes_lower, replace
destring homes_upper, replace

replace homes_lower = homes_lower*1000 if homes_lower < 100


* Destring channels
destring Channelscapicity, generate(channels) force


* Destring Top100Market
destring Top100market, generate(top100market) force


* Destring homes
destring Homeinfrontofplant, generate(homes) force


* Destring Potential
destring Potential, generate(potential) force


* Rename variables
rename State state
rename Name name
rename Copied copied
rename Locationcopied location_copied
rename Note note
rename Symbol symbol


* Reorder variables
#delimit ;
order subscribers year potential potential_lower potential_upper potential_lower
top100market population year channels homes homes_lower homes_upper note symbol
, after (location_copied);
#delimit cr


* Label variables
label variable year "When service began"
label variable potential_lower "Potential lower bound"
label variable potential_upper "Potential upper bound"
label variable homes_lower "Homes lower bound"
label variable homes_upper "Homes upper bound"


* Drop string variables
drop Population Whenservicebegan Subscribers Channelscapicity Top100market Homeinfrontofplant Potential
