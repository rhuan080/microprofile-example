	 Vue.prototype.$urlSubject = 'http://172.18.0.5:8080/';
	 Vue.prototype.$urlSpeaker = 'http://172.18.0.3:8080/';

	            

	            function loadSubjects(app){
	                axios
	                    .get(app.$urlSubject + 'subjects')
	                    .then(response => {
	                        app.subjects = response.data;
	                        app.subjects.forEach(subject => subject.date = new Date(subject.date*1000) );
	                    })
	                    .catch(error => {

	                        app.erro = true;
	                        if (error.response) {
	                            this.mensagem = error.response.data.mensagens;
	                        }
	                        else {
	                            this.mensagem = "Service failed. Try again soon.";
	                        }

	                    });
	            }

	            function loadSpeakers(app){
	                axios
	                    .get(app.$urlSpeaker + 'speakers')
	                    .then(response => {
	                        app.speakers = response.data;
	                    })
	                    .catch(error => {

	                        app.erro = true;
	                        if (error.response) {
	                            this.mensagem = error.response.data.mensagens;
	                        }
	                        else {
	                            this.mensagem = "Service failed. Try again soon.";
	                        }

	                    });
	            }

	            function treatInputDate(value){

	            	var dateSplit = value.split("-");

	            	if( dateSplit.length == 3){
	            		return new Date(dateSplit[0],dateSplit[1], dateSplit[2]);
	            	}

	            	return null;
	            }

	          
