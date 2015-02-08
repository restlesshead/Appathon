var sys = require('sys'), url = require('url'), http = require('http'), qs = require('querystring');
var cradle = require('cradle');

/*Connecting couchdb using cradle plugin */

var c = new (cradle.Connection)('http://127.0.0.1', 5984, {cache : true,raw : false});

var ola_trip = c.database('ola_trip');

/* creating node server for rest calls */
http.createServer(function(req, res) {
	if (req.method == 'POST') {
		ola_data = '';
		req.on('data', function(data) {
			ola_data += data;
		});
		req.on('end', function() {
		   if(ola_data.trip_id!=null) {
		       saveTripdetails(ola_data);
		   } else if(ola_data.userid !=null) {
		      saveUserdetails(ola_data);
		   }else if(ola_data.cab_id !=null) {
		      saveCabdetails(ola_data);
		   }
			
		});
	} else if (req.method == 'GET') {
		res.writeHead(200, {
			"Content-Type" : "text/plain"
		});
		res.end(JSON.stringify(res));
	}

}).listen(8000);

function saveTripdeatils(data) { 
	ola_trip.save(data, function(err, res) {
		res.end(JSON.stringify(res));
	});	
}

function saveUserdetails(data) { 
	ola_trip.save(data, function(err, res) {
		res.end(JSON.stringify(res));
	});	
	
	
function saveCabdetails(data) { 
	ola_trip.save(data, function(err, res) {
		res.end(JSON.stringify(res));
	});	
}