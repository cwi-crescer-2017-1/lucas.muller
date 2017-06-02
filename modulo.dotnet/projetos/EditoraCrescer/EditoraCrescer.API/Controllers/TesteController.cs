using EditoraCrescer.API.App_Start;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace EditoraCrescer.API.Controllers
{
    [BasicAuthorization(Roles = "Administrador")]
    public class TesteController : ApiController
    {
        public IHttpActionResult Get()
        {
            return Ok();
        }
    }
}
