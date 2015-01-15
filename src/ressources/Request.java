package ressources;

import java.io.Serializable;

	/**
	 * The class <code>Request</code> defines objects representing requests in the
	 * M/M/1 simulation example.
	 *
	 * <p><strong>Description</strong></p>
	 * 
	 * A request has a unique identifier (URI) and a processing time set at creation
	 * time.  When it arrives at a service provider, the arrival time can be set.
	 * The arrival time can then be used later on when the execution of the request
	 * finishes to compute the service time (waiting + processing) of the request.
	 * 
	 * As the object can be passed as parameter of a remote method call, the class
	 * implements the Java interface <code>Serializable</code>.
	 * 
	 * <p><strong>Invariant</strong></p>
	 * 
	 * <pre>
	 * invariant	processingTime > 0 && arrivalTime >= 0
	 * </pre>
	 * 
	 * <p>Created on : 2 sept. 2014</p>
	 * 
	 * @author	<a href="mailto:Jacques.Malenfant@lip6.fr">Jacques Malenfant</a>
	 * @version	$Name$ -- $Revision$ -- $Date$
	 */
	public class			Request
	implements 	Serializable
	{
		private static final long serialVersionUID = 1L;

		/** unique identifier of the request, for tracing purposes.				*/
		protected int		uri ;
		public int getUri() {
			return uri;
		}

		/** time required to execute the request.								*/
		protected long		processingTime ;
		/** time at which it has been received by the service provider.			*/
		protected long		arrivalTime ;
		
		/** l'id de l'application a qui appartient cette requete */
		protected String applicationURI;

		public String getApplicationURI() {
			return applicationURI;
		}

		public void setApplicationURI(String applicationURI) {
			this.applicationURI = applicationURI;
		}

		/**
		 * create a new request with given uri and processing time.
		 * 
		 * <p><strong>Contract</strong></p>
		 * 
		 * <pre>
		 * pre	processingTime > 0
		 * post	true			// no postcondition.
		 * </pre>
		 *
		 * @param uri				unique identifier of the nesw request.
		 * @param processingTime	time required to execute the request.
		 */
		public				Request(
			int uri,
			long processingTime,
			String applicationURI
			)
		{
			super() ;

			assert	processingTime > 0 ;

			this.uri = uri ;
			this.processingTime = processingTime ;
			this.arrivalTime = 0 ;
			this.applicationURI = applicationURI;
			assert	this.processingTime > 0 && this.arrivalTime >= 0 ;
		}

		/**
		 * return the processing time of the request.
		 * 
		 * <p><strong>Contract</strong></p>
		 * 
		 * <pre>
		 * pre	true			// no precondition.
		 * post	true			// no postcondition.
		 * </pre>
		 *
		 * @return	the time required to execute the request.
		 */
		public long			getProcessingTime() {
			return processingTime;
		}

		/**
		 * return the time at which the request has been received by the service
		 * provider.
		 * 
		 * <p><strong>Contract</strong></p>
		 * 
		 * <pre>
		 * pre	true			// no precondition.
		 * post	true			// no postcondition.
		 * </pre>
		 *
		 * @return	the time at which the request has been received by the service provider.
		 */
		public long			getArrivalTime() {
			return arrivalTime;
		}

		/**
		 * sets the time at which the request has been received by the service
		 * provider.
		 * 
		 * <p><strong>Contract</strong></p>
		 * 
		 * <pre>
		 * pre	arrivalTime > 0
		 * post	true			// no postcondition.
		 * </pre>
		 *
		 * @param arrivalTime
		 */
		public void			setArrivalTime(long arrivalTime)
		{
			assert	arrivalTime > 0 ;

			this.arrivalTime = arrivalTime;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String		toString() {
			return "" + this.uri ;
		}
	}
