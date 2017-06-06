using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TrabalhoFinal.Dominio;

namespace TrabalhoFinal.Infraestrutura.Repositorios
{
    public class PacoteRepositorio : IDisposable
    {
        private Contexto contexto = new Contexto();

        public List<Pacote> Obter()
        {
            return contexto.Pacotes.ToList();
        }

        public Pacote Obter(int id)
        {
            return contexto.Pacotes.Where(x => x.Id == id).FirstOrDefault();
        }

        public Pacote Criar(Pacote pacote)
        {
            contexto.Pacotes.Add(pacote);
            contexto.SaveChanges();
            return pacote;
        }

        public bool Alterar(Pacote pacote)
        {
            if (contexto.Pacotes.Count(x => x.Id == pacote.Id) == 0)
                return false;

            contexto.Entry(pacote).State = System.Data.Entity.EntityState.Modified;
            contexto.SaveChanges();
            return true;
        }

        public bool Excluir(int id)
        {
            Pacote pacote = contexto.Pacotes.Where(x => x.Id == id).FirstOrDefault();
            if (pacote == null)
                return false;

            contexto.Pacotes.Remove(pacote);
            contexto.SaveChanges();
            return true;
        }

        public void Dispose()
        {
            ((IDisposable)contexto).Dispose();
        }
    }
}
