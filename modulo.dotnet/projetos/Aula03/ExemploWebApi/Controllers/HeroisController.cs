using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Web.Http;
using ExemploWebApi.Models;

namespace ExemploWebApi.Controllers
{
    public class HeroisController : ApiController
    {
        private static List<Heroi> Herois;
        private static int UltimoIdHeroi;
        private static object ObjLock = new object();

        public HeroisController()
        {
            // criar uma base de heróis se lista é nula
            if (Herois == null)
            {
                Herois = new List<Heroi>();

                for (int i = 1; i < 51; i++)
                    Herois.Add(new Heroi() { Id = ++UltimoIdHeroi, Nome = $"Goku v{i}", Poder = new Poder() { Nome = $"Sei lá v{i}", Dano = 10 * i } });
            }
        }

        public IHttpActionResult Get(int? id = null)
        {
            if (id == null)
                return Ok(Herois);
            else
                return Ok(Herois.Where(h => h.Id == id).First());
        }

        public IHttpActionResult Post(Heroi heroi)
        {
            if (heroi == null)
            {
                return BadRequest();
            }
            else
            {
                lock (ObjLock) // thread safe
                {
                    heroi.Id = ++UltimoIdHeroi;
                    Herois.Add(heroi);
                }

                return Ok(heroi);
            }
        }
    }
}
