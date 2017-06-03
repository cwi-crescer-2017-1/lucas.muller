using EditoraCrescer.Infraestrutura.Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Data.Entity;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace EditoraCrescer.Infraestrutura.Repositorios
{
    public class LivroRepositorio : IDisposable
    {
        private Contexto contexto = new Contexto();

        public LivroRepositorio()
        { }

        public List<Livro> Obter()
        {
            return contexto.Livros.Include(x => x.Autor).Include(x => x.Revisor).ToList();
        }

        public dynamic ObterFormaResumidaComPaginacao(int limit, int page)
        {
            dynamic livros = contexto.Livros.Where(x => x.DataPublicacao!=null).OrderByDescending(x => x.DataPublicacao)
                .Skip(limit*(page-1)).Take(limit)
                .Select(livro => new
                {
                    Isbn = livro.Isbn,
                    Titulo = livro.Titulo,
                    Capa = livro.Capa,
                    Autor = livro.Autor.Nome,
                    Genero = livro.Genero
                }).ToList();
            var quantPaginas = (int)Math.Ceiling((double)contexto.Livros.Count() / limit);

            return new
            {
                Livros = livros,
                Pagina = page,
                QuantPaginas = quantPaginas,
                UltimaPagina = page >= quantPaginas
            };
        }

        public dynamic ObterFormaResumida()
        {
            return contexto.Livros.Where(x => x.DataPublicacao != null).Select(livro => new
            {
                Isbn = livro.Isbn,
                Titulo = livro.Titulo,
                Capa = livro.Capa,
                Autor = livro.Autor.Nome,
                Genero = livro.Genero
            }).ToList();
        }

        public dynamic ObterLancamentosFormaResumida()
        {
            DateTime diaDeHojeMenosSeteDias = DateTime.Now.AddDays(-7);
            return contexto.Livros
                            .Include(x => x.Autor)
                            .OrderByDescending(x => x.DataPublicacao)
                            .Where(x => x.DataPublicacao != null && x.DataPublicacao >= diaDeHojeMenosSeteDias)
                            .Select(livro => new
                            {
                                Isbn = livro.Isbn,
                                Titulo = livro.Titulo,
                                Capa = livro.Capa,
                                Autor = livro.Autor.Nome,
                                Genero = livro.Genero
                            })
                            .ToList();
        }

        public dynamic ObterLancamentosFormaResumida(int limit)
        {
            DateTime diaDeHojeMenosSeteDias = DateTime.Now.AddDays(-7);
            return contexto.Livros
                            .Include(x => x.Autor)
                            .OrderByDescending(x => x.DataPublicacao)
                            .Where(x => x.DataPublicacao >= diaDeHojeMenosSeteDias)
                            .Take(limit)
                            .Select(livro => new
                            {
                                Isbn = livro.Isbn,
                                Titulo = livro.Titulo,
                                Capa = livro.Capa,
                                Autor = livro.Autor.Nome,
                                Genero = livro.Genero
                            })
                            .ToList();
        }

        public Livro Obter(int isbn)
        {
            return contexto.Livros.Include(x => x.Autor).Include(x => x.Revisor).FirstOrDefault(x => x.Isbn == isbn);
        }

        public List<Livro> ObterPorGenero(string genero)
        {
            return contexto.Livros.Where(x => x.Genero.Contains(genero)).Include(x => x.Autor).Include(x => x.Revisor).ToList();
        }

        public dynamic ObterPorGeneroFormaResumida(string genero)
        {
            return contexto.Livros.Where(x => x.Genero.Contains(genero))
                                  .Select(livro => new
                                  {
                                      Isbn = livro.Isbn,
                                      Titulo = livro.Titulo,
                                      Capa = livro.Capa,
                                      Autor = livro.Autor.Nome,
                                      Genero = livro.Genero
                                  })
                                  .ToList();
        }

        public Livro Criar(Livro livro)
        {
            var livroCriado = contexto.Livros.Add(livro);
            contexto.SaveChanges();
            return livroCriado;
        }

        public bool Atualizar(Livro livro)
        {
            // verifica se livro existe
            if (contexto.Livros.Count(x => x.Isbn == livro.Isbn) < 1) return false;

            contexto.Entry(livro).State = System.Data.Entity.EntityState.Modified;
            contexto.SaveChanges();
            return true;
        }

        public bool Revisar(int idRevisor, int isbn)
        {
            var livro = contexto.Livros.FirstOrDefault(x => x.Isbn == isbn);
            if (livro == null)
            {
                return false;
            }
            else
            {
                livro.IdRevisor = idRevisor;
                livro.DataRevisao = DateTime.Now;
                contexto.Entry(livro).State = System.Data.Entity.EntityState.Modified;
                contexto.SaveChanges();
                return true;
            }
        }

        public bool Publicar(int isbn)
        {
            var livro = contexto.Livros.FirstOrDefault(x => x.Isbn == isbn);
            if (livro == null)
            {
                return false;
            }
            else
            {
                livro.DataPublicacao = DateTime.Now;
                contexto.Entry(livro).State = System.Data.Entity.EntityState.Modified;
                contexto.SaveChanges();
                return true;
            }
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

        public void Dispose()
        {
            ((IDisposable)contexto).Dispose();
        }
    }
}
