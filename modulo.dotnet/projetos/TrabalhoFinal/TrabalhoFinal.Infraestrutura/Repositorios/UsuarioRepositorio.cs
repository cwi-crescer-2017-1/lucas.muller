using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TrabalhoFinal.Infraestrutura.Entidades;

namespace TrabalhoFinal.Infraestrutura.Repositorios
{
    public class UsuarioRepositorio : IDisposable
    {
        private Contexto contexto = new Contexto();

        public Usuario Obter(string email)
        {
            return contexto.Usuarios.Where(x => x.Email == email).FirstOrDefault();
        }

        public Usuario Obter(int id)
        {
            return contexto.Usuarios.Where(x => x.Id == id).FirstOrDefault();
        }

        public Usuario Criar(Usuario usuario)
        {
            contexto.Usuarios.Add(usuario);
            contexto.SaveChanges();
            return usuario;
        }

        public bool Alterar(Usuario usuario)
        {
            if (VerificaExistencia(usuario.Id) == false)
                return false;

            contexto.Entry(usuario).State = System.Data.Entity.EntityState.Modified;
            contexto.SaveChanges();
            return true;
        }

        public bool Excluir(int id)
        {
            Usuario usuario = Obter(id);
            if (usuario == null)
                return false;

            contexto.Usuarios.Remove(usuario);
            contexto.SaveChanges();
            return true;
        }

        private bool VerificaExistencia(int id)
        {
            return contexto.Usuarios.Count(x => x.Id == id) >= 0;
        }

        private bool VerificaExistencia(string email)
        {
            return contexto.Usuarios.Count(x => x.Email == email) >= 0;
        }

        public void Dispose()
        {
            ((IDisposable)contexto).Dispose();
        }
    }
}
