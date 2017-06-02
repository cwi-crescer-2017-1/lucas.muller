using EditoraCrescer.Infraestrutura.Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EditoraCrescer.Infraestrutura.Repositorios
{
    public class UsuarioRepositorio : IDisposable
    {
        private Contexto contexto = new Contexto();

        //static UsuarioRepositorio()
        //{
        //    // YWRtaW5AY3dpLmNvbS5icjoxMjM0NTY=
        //    var usrAdmin = new Usuario("admin", "admin@cwi.com.br", "123456");
        //    usrAdmin.AtribuirPermissoes("Administradores");
        //    _usuarios.Add(usrAdmin.Email, usrAdmin);

        //    // Z2lvdmFuaUBjd2kuY29tLmJyOjEyMzQ1Ng==
        //    var usrGiovani = new Usuario("giovani", "giovani@cwi.com.br", "123456");
        //    _usuarios.Add(usrGiovani.Email, usrGiovani);
        //}

        public UsuarioRepositorio()
        { }

        public Usuario Criar(Usuario usuario)
        {
            var usuarioCriado = contexto.Usuarios.Add(usuario);
            contexto.SaveChanges();
            AtribuirPermissao(usuarioCriado.Id, 2); // 2 é permissao colaborador
            return usuarioCriado;
        }

        public bool Alterar(Usuario usuario)
        {
            // verifica se usuario existe
            if (contexto.Usuarios.Count(x => x.Id == usuario.Id) < 1) return false;

            contexto.Entry(usuario).State = System.Data.Entity.EntityState.Modified;
            contexto.SaveChanges();
            return true;
        }

        public bool AtribuirPermissao(int idUsuario, int idPermissao)
        {
            var usuario = contexto.Usuarios.FirstOrDefault(x => x.Id == idUsuario);
            var permissao = contexto.Permissoes.FirstOrDefault(x => x.Id == idPermissao);
            if (usuario == null || permissao == null)
            {
                return false;
            }
            else
            {
                usuario.Permissoes.Add(permissao);
                contexto.SaveChanges();
                return true;
            }
        }
        
        public bool Excluir(int id)
        {
            var usuario = contexto.Usuarios.FirstOrDefault(x => x.Id == id);
            if (usuario == null)
            {
                return false;
            }
            else
            {
                contexto.Usuarios.Remove(usuario);
                contexto.SaveChanges();
                return true;
            }
        }

        public IEnumerable<Usuario> Listar()
        {
            return contexto.Usuarios;
        }

        public Usuario Obter(string email)
        {
            return contexto.Usuarios.Include("Permissoes").Where(u => u.Email == email).FirstOrDefault();
        }

        public void Dispose()
        {
            ((IDisposable)contexto).Dispose();
        }
    }
}
