class Tasks {
	
	constructor(id, taskDetails) {
		this.id = id;
		this.taskDetails = taskDetails;		
	}

	getId() {
		return this.id;
	}

	getTaskDetails() {
		return this.taskDetails;
	}
	 
	 static getClassName() {
        return "Task";
    }

}
