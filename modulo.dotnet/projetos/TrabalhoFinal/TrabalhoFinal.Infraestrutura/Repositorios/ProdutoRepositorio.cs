using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TrabalhoFinal.Dominio;

namespace TrabalhoFinal.Infraestrutura.Repositorios
{
    public class ProdutoRepositorio : IDisposable
    {
        private Contexto contexto = new Contexto();

        public List<Produto> Obter()
        {
            return contexto.Produtos.ToList();
        }

        public Produto Obter(int id)
        {
            return contexto.Produtos.Where(x => x.Id == id).FirstOrDefault();
        }

        public Produto Criar(Produto p)
        {
            contexto.Produtos.Add(p);
            contexto.SaveChanges();
            return p;
        }

        public bool Alterar(Produto prod)
        {
            if (contexto.Produtos.Count(x => x.Id == prod.Id) == 0)
                return false;

            contexto.Entry(prod).State = System.Data.Entity.EntityState.Modified;
            contexto.SaveChanges();
            return true;
        }

        public bool Excluir(int id)
        {
            Produto produto = contexto.Produtos.Where(x => x.Id == id).FirstOrDefault();
            if (produto == null)
                return false;

            contexto.Produtos.Remove(produto);
            contexto.SaveChanges();
            return true;
        }

        public void Dispose()
        {
            ((IDisposable)contexto).Dispose();
        }
    }
}
