using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Chat.Models
{
    public class Usuario
    {
        public static int UltimoID = 0;

        public int Id { get; set; }
        public string Nome { get; set; }
        public string FotoUrl { get; set; }
    }
}