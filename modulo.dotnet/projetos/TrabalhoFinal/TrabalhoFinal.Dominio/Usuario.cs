using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace TrabalhoFinal.Dominio
{
    public class Usuario
    {
        public int Id { get; set; }
        public string Nome { get; set; }
        public string Email { get; set; }
        public string Senha { get; set; }
        public bool Gerente { get; set; }

        protected Usuario() { }

        public Usuario(string nome, string email, string senha)
        {
            Nome = nome;
            Email = email;
            Id = 0;
            if (!string.IsNullOrWhiteSpace(senha))
                Senha = CriptografarSenha(senha);
        }

        public void NovaSenha(string senha)
        {
            Senha = CriptografarSenha(senha);
        }

        private string CriptografarSenha(string senha)
        {
            MD5 md5 = MD5.Create();
            byte[] inputBytes = Encoding.Default.GetBytes(Email + senha);
            byte[] hash = md5.ComputeHash(inputBytes);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hash.Length; i++)
                sb.Append(hash[i].ToString("x2"));

            return sb.ToString();
        }

        public bool ValidarSenha(string senha)
        {
            return CriptografarSenha(senha) == Senha;
        }

        public bool Validar(out List<string> Mensagens)
        {
            Mensagens = new List<string>();

            if (string.IsNullOrWhiteSpace(Nome))
                Mensagens.Add("Nome é inválido.");

            if (string.IsNullOrWhiteSpace(Email))
                Mensagens.Add("Email é inválido.");

            if (string.IsNullOrWhiteSpace(Senha))
                Mensagens.Add("Senha é inválido.");

            return Mensagens.Count == 0;
        }
    }
}
