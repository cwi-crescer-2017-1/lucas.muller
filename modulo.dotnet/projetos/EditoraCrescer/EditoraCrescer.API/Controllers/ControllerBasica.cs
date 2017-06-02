using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web;
using System.Web.Http;

namespace EditoraCrescer.API.Controllers
{
    public class ControllerBasica : ApiController
    {

        public HttpResponseMessage ResponderOK(object resultado = null)
        {
            return Request.CreateResponse(HttpStatusCode.OK, resultado);
        }

        public HttpResponseMessage ResponderErro(params string[] mensagens)
        {
            return Request.CreateResponse(HttpStatusCode.BadRequest, new { mensagens });
        }

        public HttpResponseMessage ResponderErro(IEnumerable<string> mensagens)
        {
            return Request.CreateResponse(HttpStatusCode.BadRequest, new { mensagens });
        }
    }
}