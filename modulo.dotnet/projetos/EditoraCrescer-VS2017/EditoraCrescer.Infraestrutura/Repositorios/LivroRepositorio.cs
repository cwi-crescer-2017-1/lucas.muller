using EditoraCrescer.Infraestrutura.Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EditoraCrescer.Infraestrutura.Repositorios
{
    public class LivroRepositorio
    {
        private Contexto contexto = new Contexto();

        public LivroRepositorio()
        { }

        public List<Livro> Obter()
        {
            return contexto.Livros.ToList();
        }

        public Livro Obter(int isbn)
        {
            return contexto.Livros.FirstOrDefault(x => x.Isbn == isbn);
        }

        public Livro Criar(Livro livro)
        {
            var livroCriado = contexto.Livros.Add(livro);
            contexto.SaveChanges();
            return livroCriado;
        }

        public bool Excluir(int isbn)
        {
            var livro = contexto.Livros.FirstOrDefault(x => x.Isbn == isbn);
            if(livro == null)
            {
                return false;
            }
            else
            {
                contexto.Livros.Remove(livro);
                contexto.SaveChanges();
                return true;
            }
        }
    }
}
