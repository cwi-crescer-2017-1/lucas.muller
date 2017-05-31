using EditoraCrescer.Infraestrutura.Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EditoraCrescer.Infraestrutura.Repositorios
{
    public class AutorRepositorio : IDisposable
    {
        private Contexto contexto = new Contexto();

        public AutorRepositorio()
        { }

        public List<Autor> Obter()
        {
            return contexto.Autores.ToList();
        }

        public Autor Obter(int id)
        {
            return contexto.Autores.FirstOrDefault(x => x.Id == id);
        }

        public List<Livro> ObterLivros(int id)
        {
            return contexto.Livros.Where(x => x.IdAutor == id).ToList();
        }

        public Autor Criar(Autor autor)
        {
            var autorCriado = contexto.Autores.Add(autor);
            contexto.SaveChanges();
            return autorCriado;
        }

        public bool Alterar(Autor autor)
        {
            // verifica se autor existe
            if (contexto.Autores.Count(x => x.Id == autor.Id) < 1) return false;

            contexto.Entry(autor).State = System.Data.Entity.EntityState.Modified;
            contexto.SaveChanges();
            return true;
        }

        public bool Excluir(int id)
        {
            var autor = contexto.Autores.FirstOrDefault(x => x.Id == id);
            if (autor == null)
            {
                return false;
            }
            else
            {
                contexto.Autores.Remove(autor);
                contexto.SaveChanges();
                return true;
            }
        }

        public void Dispose()
        {
            ((IDisposable)contexto).Dispose();
        }
    }
}
