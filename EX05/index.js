const express = require('express');
const fs = require('fs')
const path = './tasks.json';
const bodyParser = require('body-parser');
const PORT = 8080;
let app = express();
app.use(bodyParser.json());


app.get('/tasks', (req, res) => {
    console.log("Got 'Get' request");
	let showTasks;
	fs.readFile(path, (err, content)=> {
		if (err){
			     throw err;				
		}
    showTasks = content;
    res.send(JSON.parse(showTasks));
	});
});

app.post('/tasks/new', (req, res) => {
	let updateList;
	let jsonTask;
	console.log("Got 'Post' request");
	fs.readFile(path, (err, content)=> {
		if (err){
			     throw err;				
		}
	jsonTask = JSON.parse(content);
	
	let taskId = req.query.id;
	let taskDetails = req.query.task;	
	
	jsonTask[taskId] = taskDetails;
	//tasksJson.tasks.id = taskDetails;
	updateList = JSON.stringify(jsonTask, null);
	
	fs.writeFile(path, updateList, (err) =>{
		if (err){
			throw err;				
		} 
		else{
			res.send('New task added!');
		}
		
		});
    });
});


app.post('/tasks/remove', (req, res) => {
	let updateList;
	let jsonTask;
	console.log("Got 'DELETE' request");
    fs.readFile(path, (err, content)=> {
	if (err){
		throw err;
	}
        
	jsonTask = JSON.parse(content);
	
	let removeTask = req.query.id;
	
	delete jsonTask[removeTask];
	updateList = JSON.stringify(jsonTask);
    
    fs.writeFile(path, updateList, (err) =>{
        if(err) {
            throw err;
        } else {
            res.send('Task Removed succesfully!');
        }
		});
    });
});
	
app.listen(8080, () => console.log(`Server is running on port 8080!`));