package com.example.gestiondemusica.utils.extension

import androidx.core.text.isDigitsOnly

/* Disc Number */
val String.getNumDisc: String
    get() {
        var arrNumDisc = this.split("/")

        if(arrNumDisc.isEmpty() || arrNumDisc[0] == "") {
            return "";
        }

        if(arrNumDisc[0].isDigitsOnly()) {
            return arrNumDisc[0];
        }

        return ""
    }

val String.getTotalNumDisc: String
    get() {
        var arrTotalNumDisc = this.split("/")

        if(arrTotalNumDisc.isEmpty() || arrTotalNumDisc[0] == "") {
            return "";
        }

        if(arrTotalNumDisc.size > 1 ) {
            if(arrTotalNumDisc[1].isDigitsOnly()) {
                return arrTotalNumDisc[1];
            }
        }

        return "";
    }

/* CD Track */
val String.getTotalCdTrackNumber: String
    get() {
        if(this.split("/")[0]=="") {
            return ""
        }else {
            return this.split("/")[1]
        }
    }

val String.getNumCdTrackNumber: String
    get() {
        if(this.split("/")[0]=="") {
            return ""
        }else {
            return this.split("/")[0]
        }
    }





