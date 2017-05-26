using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Chat.Models
{
    public class Mensagem
    {
        public static int UltimoID = 0;
        
        public int Id { get; set; }
        private string _Texto;
        public string Texto {
            get { return _Texto; }
            set
            {
                if (_Texto != null) Editada = true;
                _Texto = value;
            }
        }
        public Boolean Editada { get; private set; }
        public int IdUsuarioAutor { get; set; }
        public readonly DateTime EnviadoEm = DateTime.Now;
    }
}