using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TrabalhoFinal.Dominio;

namespace TrabalhoFinal.Infraestrutura.Repositorios
{
    public class OpcionalRepositorio : IDisposable
    {
        private Contexto contexto = new Contexto();

        public List<Opcional> Obter()
        {
            return contexto.Opcionais.ToList();
        }

        public Opcional Obter(int id)
        {
            return contexto.Opcionais.Where(x => x.Id == id).FirstOrDefault();
        }

        public Opcional Criar(Opcional op)
        {
            contexto.Opcionais.Add(op);
            contexto.SaveChanges();
            return op;
        }

        public bool Alterar(Opcional op)
        {
            if (contexto.Opcionais.Count(x => x.Id == op.Id) == 0)
                return false;

            contexto.Entry(op).State = System.Data.Entity.EntityState.Modified;
            contexto.SaveChanges();
            return true;
        }

        public bool Excluir(int id)
        {
            Opcional pacote = contexto.Opcionais.Where(x => x.Id == id).FirstOrDefault();
            if (pacote == null)
                return false;

            contexto.Opcionais.Remove(pacote);
            contexto.SaveChanges();
            return true;
        }

        public void Dispose()
        {
            ((IDisposable)contexto).Dispose();
        }
    }
}
