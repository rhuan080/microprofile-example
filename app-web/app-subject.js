				var appSubject = new Vue({
	                el: '#app-subject',
	                data: {
	                    subjects: [],
	                    mensagem : null,
	                    erro: false,
	                },
	                appNewSubject:{},
	                mounted () {
	                   loadSubjects(this);
	                },
	                methods:{
		                loadNewSubject: function(){

		                	if(this.appNewSubject){
		                		return;
		                	}

		                	this.appNewSubject =  new Vue({
				                el: '#app-new-subject',
				                data: {
				                    speakers: [],
				                    mensagem : null,
				                    erro: false,
				                    form:{
				                        subject:'',
				                        description:'',
				                        date:'',
				                        hour:'',
				                        minute:'',
				                        speaker:'',
				                        erro: false,
				                        mensagemErros:''
				                    }

				                },
				                methods: {
				                    save: function (e) {
				                        var config = {
				                            headers: {
				                            	'Content-Type': 'application/json',
				                            	'Origin': document.URL
				                        	}
				                        };

				                        var data = { 
				                        	         'title': this.form.subject,
				                                     'description': this.form.description,
				                                     'date': treatInputDate(this.form.date).getTime()/1000,
				                                     'hour': this.form.hour,
				                                     'minute' : this.form.minute,
				                                     'idSpeaker': this.form.speaker
				                        }

				                        axios
				                            .post(this.$urlSubject+"subjects" ,data, config )
				                            .then(response => {
				                                loadSubjects(appSubject);
				                                this.closeModal();
				                            })
				                            .catch(error => {

				                                this.form.erro = true;
				                                if (error.response) {
				                                    this.form.mensagemErros = error.response.data.mensagens;
				                                }
				                                else {
				                                    this.form.mensagemErros = ["Service failed. Try again soon."];
				                                }
				                            });

				                        
				                         	


				                    },
				                    closeModal:function () {
				                    	$('#app-new-subject').modal('hide');
				                    	this.cleanForm();
				                    },
				                    cleanForm: function (){
				                        this.form ={
					                        subject:'',
					                        description:'',
					                        date:'',
					                        hour:'',
					                        minute:'',
					                        speaker:'',
					                        erro: false,
					                        mensagemErros:''
				                    	}
				                    }
				                },
				                mounted () {
				                   loadSpeakers(this);
				                }

				            });
		                }
	                

	                }

	            });

	          

	          
