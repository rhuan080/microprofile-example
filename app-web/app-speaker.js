 			var appSpeaker = new Vue({
	                el: '#app-speaker',
	                data: {
	                    speakers: [],
	                    mensagem : null,
	                    erro: false,
	                },
	                appNewSpeaker:{},
	                mounted () {
	                   loadSpeakers(this);
	                },
	                methods:{
		                loadNewSpeaker: function(){

		                	if(this.appNewSpeaker){
		                		return;
		                	}
		                	
		                	this.appNewSpeaker =  new Vue({
				                el: '#app-new-speaker',
				                data: {
				                    mensagem : null,
				                    erro: false,
				                    form:{
				                        name:'',
				                        description:'',
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
				                        	         'name': this.form.name,
				                                     'description': this.form.description,
				                        }

				                        axios
				                            .post(this.$urlSpeaker+"speakers" ,data, config )
				                            .then(response => {
				                                loadSpeakers(appSpeaker);
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
				                         	this.closeModal();


				                    },
				                    closeModal:function () {
				                    	$('#app-new-speaker').modal('hide');
				                    	this.cleanForm();
				                    },
				                    cleanForm: function (){
				                        this.form ={
					                        name:'',
					                        description:'',
					                        erro: false,
					                        mensagemErros:''
				                    	}
				                    }
				                },
				               

				            });
		                }
	                

	                }

	          });