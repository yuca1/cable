local dta_name "cable_yearbook.dta"            /*set data name*/

import excel using "cable1968.xlsx", sheet("Alabama") firstrow

save "`dta_name'", replace
clear

#delimit;
local statenames "Alaska Arizona Arkansas California Colorado Delaware DC Florida Georgia Hawaii 
Idaho Illinois Indiana Iowa Kansas Kentucky Louisiana Maine Maryland 
Massachusetts Michigan Minnesota Mississippi Missouri Montana 
Nebraska Nevada NewHampshire NewJersey NewMexico NewYork NorthCarolina NorthDakota Ohio Oklahoma Oregon Pennsylvania 
RhodeIsland SouthCarolina SouthDakota Tennessee Texas Utah Vermont Virginia Washington WestVirginia Wisconsin Wyoming";
#delimit cr;

foreach var of local statenames {
    import excel using "cable1968.xlsx", sheet("`var'") firstrow
    append using "`dta_name'", force
    save "`dta_name'", replace
    clear    
}
