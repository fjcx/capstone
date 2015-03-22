package edu.harvard.capstone.result

import edu.harvard.capstone.user.Scientist
import edu.harvard.capstone.parser.Equipment
import edu.harvard.capstone.editor.ExperimentalPlateSet

class Result {

	Scientist owner
	Equipment equipment
	ExperimentalPlateSet experiment
	String name
	String description

    static constraints = {
    	description blank: true, nullable: true
    }

	static mapping = {
		description type: 'text'
	}

}
